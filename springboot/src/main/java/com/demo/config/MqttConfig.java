package com.demo.config;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import com.demo.javaBean.Message;
import com.demo.javaBean.Location;
import com.demo.repository.MessageRepository;
import com.demo.serveice.LocationService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.StringTokenizer;

@Configuration
public class MqttConfig {

    @Value("${mqtt.url}")
    private String mqttUrl;

    @Value("${mqtt.username}")
    private String mqttUsername;

    @Value("${mqtt.password}")
    private String mqttPassword;

    @Value("${mqtt.clientId}")
    private String mqttClientId;

    @Value("${mqtt.topic}")
    private String mqttTopic;

    @Value("${mqtt.locationTopic}")
    private String mqttLocationTopic;

    private final SimpMessagingTemplate template;
    private final MessageRepository messageRepository;
    private final LocationService locationService;

    @Autowired
    public MqttConfig(SimpMessagingTemplate template,
                      @Qualifier("messageRepository") MessageRepository messageRepository,
                      LocationService locationService) {
        this.template = template;
        this.messageRepository = messageRepository;
        this.locationService = locationService;
    }

    @Bean
    public MqttConnectOptions mqttConnectOptions() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(new String[]{mqttUrl});
        options.setUserName(mqttUsername);
        options.setPassword(mqttPassword.toCharArray());
        options.setAutomaticReconnect(true);
        return options;
    }

    @Bean
    public DefaultMqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(mqttConnectOptions());
        return factory;
    }

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(mqttClientId, mqttClientFactory(), mqttTopic, mqttLocationTopic);
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return message -> {
            String topic = message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC).toString();
            String payload = message.getPayload().toString();
            System.out.println("Received message from topic: " + topic);
            System.out.println("Payload: " + payload);

            try {
                if (topic.equals(mqttTopic)) {
                    // 处理原有主题的消息
                    StringTokenizer tokenizer = new StringTokenizer(payload, " ");
                    double temperature = Double.parseDouble(tokenizer.nextToken());
                    double humidity = Double.parseDouble(tokenizer.nextToken());
                    double light = Double.parseDouble(tokenizer.nextToken());
                    double distance = Double.parseDouble(tokenizer.nextToken());

                    Message msg = new Message();
                    msg.setTopic(topic);
                    msg.setTemperature(temperature);
                    msg.setHumidity(humidity);
                    msg.setLight(light);
                    msg.setDistance(distance);
                    messageRepository.save(msg);

                    // 发送消息到 WebSocket 主题
                    template.convertAndSend("/topic/greetings", msg);
                } else if (topic.equals(mqttLocationTopic)) {
                    // 处理经纬度主题的消息
                    ObjectMapper mapper = new ObjectMapper();
                    Location location = mapper.readValue(payload, Location.class);
                    locationService.handleMessage(location);

                    // 发送消息到 WebSocket 主题
                    template.convertAndSend("/topic/locations", location);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}

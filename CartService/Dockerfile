version: '8.8'
services:
  elasticsearch:
    image: docker.elastic.co/elasticsearch/elasticsearch:8.8.2
    container_name: elasticsearch
    environment:
      - discovery.type=single-node
      - xpack.security.enabled=false
      - "ES_JAVA_OPTS=-Xms512m -Xmx512m"
    ports:
      - "9200:9200"
    networks:
      - elastic

  logstash:
    image: docker.elastic.co/logstash/logstash:8.8.2
    container_name: logstash
    environment:
      - xpack.monitoring.enabled=false
    volumes:
      - ./logstash.conf:/usr/share/logstash/pipeline/logstash.conf
    ports:
      - "5044:5044"
      - "9600:9600"
    depends_on:
      - elasticsearch
    networks:
      - elastic

  kibana:
    image: docker.elastic.co/kibana/kibana:8.8.2
    container_name: kibana
    environment:
      - ELASTICSEARCH_HOSTS=http://elasticsearch:9200
    ports:
      - "5601:5601"
    depends_on:
      - elasticsearch
    networks:
      - elastic

networks:
  elastic:
    driver: bridge

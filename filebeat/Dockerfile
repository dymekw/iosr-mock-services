FROM docker.elastic.co/beats/filebeat:6.1.1
ADD /filebeat/filebeat.yml /usr/share/filebeat/filebeat.yml
USER root
RUN chown filebeat /usr/share/filebeat/filebeat.yml
USER filebeat
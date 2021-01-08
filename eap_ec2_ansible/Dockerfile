FROM ubuntu:18.04

ENV DEBIAN_FRONTEND=noninteractive

RUN apt-get update \
    && apt install -y software-properties-common git \
    && apt install -y python-pip

RUN mkdir -p /var/workdir/
RUN mkdir -p /var/ansible/

RUN git clone https://github.com/f5devcentral/f5-ansible-cloudservices.git /var/ansible
WORKDIR /var/ansible
RUN pip install -r requirements.txt
RUN pip install boto
RUN ansible-galaxy collection build --force
RUN ansible-galaxy collection install f5devcentral-cloudservices-1.1.0.tar.gz -f -p  /var/workdir/ansible/collections/

COPY . /var/workdir
WORKDIR /var/workdir
CMD ["bash", "run.sh"]
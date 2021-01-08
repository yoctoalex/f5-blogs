# socket issue https://ttl255.com/ansible-fixing-issues-with-control-socket/
DIR="/root/.ansible/pc/"
if [ -d "$DIR" ]; then
  echo "Removing previous socket connections in ${DIR}..."
  rm -rf ${DIR}/*
fi

ansible-playbook ./ansible/playbook.yml -i hosts.yml -vvv

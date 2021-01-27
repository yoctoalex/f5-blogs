pipeline {
    agent any

    stages {
        stage('Clone sources') {
            steps {
                git url: 'https://github.com/yoctoalex/f5-blogs.git'
            }
        }
        stage('Install F5 ansible collection and requirements') {
            steps {
                sh "ansible-galaxy collection install f5devcentral.cloudservices -p ./eap_ec2_ansible/ansible/collections"
                sh "pip3 install -r ./eap_ec2_ansible/ansible/collections/ansible_collections/f5devcentral/cloudservices/requirements.txt"
                sh "pip3 install boto"
            }
        }
        stage('Deploy F5 Essential App Protected environment') {
          steps {
              ansiblePlaybook([
                inventory   : './eap_ec2_ansible/hosts.yml',
                playbook    : './eap_ec2_ansible/ansible/playbook.yml',
                extras: '-vvv'
              ])
          }
        }
    }
}

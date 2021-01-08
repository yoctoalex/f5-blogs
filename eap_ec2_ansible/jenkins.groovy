pipeline {
    agent any

    stages {
        stage('Setup new instance') {
          steps {
            dir("/var/f5blogs/eap_ec2_ansible/") {
              ansiblePlaybook([
                inventory   : 'hosts.yml',
                playbook    : './ansible/playbook.yml',
                colorized   : true,
                extras: '-vvv'
              ])
            }
          }
        }
    }
}

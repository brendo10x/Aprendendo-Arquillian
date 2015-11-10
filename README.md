# Aprendendo-Arquillian
Este projeto realiza testes com Arquillian utilizando os recursos do Java EE 7

Configuração Wildfly remote

No pom.xml acrescente o perfil:
 <!-- Arquillian WildFly remote profile -->
        <profile>
            <id>arq-widlfly-remote</id>
            <dependencies>
                <dependency>
                    <groupId>org.wildfly</groupId>
                    <artifactId>wildfly-arquillian-container-remote</artifactId>
                    <version>${version.org.wildfly}</version>
                    <scope>test</scope>
                </dependency>
            </dependencies>
        </profile>
        
Por fim, adicione no arquivo arquillian.xml a seguinte instrução:

 <!-- Sets the protocol which is how Arquillian talks and executes the tests inside the container -->
    <defaultProtocol type="Servlet 3.0" />

    <!-- Configuration to be used when the WidlFly remote profile is active -->
    <container qualifier="widlfly-remote">
        <configuration>
            <property name="managementAddress">127.0.0.1</property>
            <property name="managementPort">9990</property>
            <property name="username">admin</property>
            <property name="password">admin</property>
        </configuration>
    </container>

Melhorando o a chamada para o container wildfly gerenciado:    
<!-- Configuration to be used when the WildFly managed profile is active -->
	<container qualifier="widlfly-managed" default="true">
		<configuration>
			<property name="jbossHome">D:\wildfly-8.1.0.Final</property>
			<property name="allowConnectingToRunningServer">true</property>
		</configuration>
	</container>

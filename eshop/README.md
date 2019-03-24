# eshop Application

The `eshop` application processes the order details of a bill, applies the relevant 
discounts and generates the final bill due for payment.

Pre-requisites
--------------

Please note the below pre-requisites for downloading and running the application.

`JDK 1.8` or above

`Maven 3.1.0` or above

`Junit 5.4.1` or above

Instructions for downloading the source code from Github


Compiling the application
-------------------------

Please ensure `JAVA_HOME` is correctly pointing to the JDK installation directory
(In Windows, you can use `set JAVA_HOME=_jdk_install_dir_`. In Unix, you can use `export JAVA_HOME=<jdk_install_dir>`.)

Please ensure `MAVEN_HOME` is correctly pointing to the Maven installation directory
(In Windows, you can use `set MAVEN_HOME=<maven_install_dir>`. In Unix, you can use `export MAVEN_HOME=<maven_install_dir>`.)

Run the following commands from the directory where the zip file downloaded from Github was extracted. 

Below steps assume that you have extracted the zip file contents to `c:\Development\eshop` directory

`c:\Development\eshop\mvn compile`

`c:\Development\eshop\mvn test-compile`

`c:\Development\eshop\mvn test`

`c:\Development\eshop\mvn install`


Running the application
-------------------------

Run the below command to execute the eshop application

`c:\Development\eshop\java -jar .\target\eshop-1.0.jar <order file> <user type> <user type>`

User type (Possible values : EMPLOYEE, AFFILIATE, LOYALIST)

Please note the following when running the eshop application

Two parameters `order file` and `user type` are required mandatorily to run the application. If they are not passed, the application will use the `input\order.csv` by default for processing.




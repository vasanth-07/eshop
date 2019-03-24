# eshop Application

The `eshop` application processes the order details of a bill, applies the relevant 
discounts and generates the final bill due for payment.

Pre-requisites
--------------

Please note the below software are installed before downloading and running the application.

`JDK 1.8` or above

`Maven 3.1.0` or above

`Junit 5.4.1` or above

Downloading the source code from Github
---------------------------------------
You can download the source code from [Github](https://github.com/vasanth-07/eshop-repo/tree/develop). 

Click the `Clone or download` button to download the source in zip format.


Compiling the application
-------------------------

Please ensure `JAVA_HOME` is correctly pointing to the JDK installation directory
(In Windows, you can use `set JAVA_HOME=<jdk_install_dir>`. In Unix, you can use `export JAVA_HOME=<jdk_install_dir>`.)

Please ensure `MAVEN_HOME` is correctly pointing to the Maven installation directory
(In Windows, you can use `set MAVEN_HOME=<maven_install_dir>`. In Unix, you can use `export MAVEN_HOME=<maven_install_dir>`.)

To compile the application, run the following commands from the directory where the zip file downloaded from Github was extracted. 

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

Creating your own Order file
----------------------------
An input file containing the order details is mandatory for running the application. A sample file `input\order.csv` is provided as part of the source code. Format of the file is as below. You can use the format to create your own file. Each line represents an order item. There are 6 data elements in an order item and each value is comma separated.


| Element             | Data Type | Possible Values
| ------------------- | --------- | ---------------|
| Product Id    	  | String    | |
| Product Name        | String    | |
| Product Description | String    | |
| Product Type		  | String    | GROCERY, ELECTRONICS, MEDICAL |
| Unit Price		  | double    | |
| Quantity 			  | int       | |

| Command | Description |
| --- | --- |
| `git status` | List all *new or modified* files |
| `git diff` | Show file differences that **haven't been** staged |

**Example entry**

*p1,Milk Powder,InfaGrow Milk Powder,GROCERY,80.30,2*




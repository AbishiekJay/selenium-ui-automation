# selenium-ui-automation

## This is my customized framework for Selenium UI Automation

###### The framework consists of features like Modularized codes, DataProvider class , customized driver methods, logging of failures, reporting features.

** Modularized Codes **

The Modularized approach is based on POM (Page Object Model) where the Page Classes are separate from Test class

Page Classes contains the selectors of the pages and the methods(or actions) on that page.
Each page of the target application has the seperate pages.
The Page classes interact with the target application and any changes in the UI elements (like change in classname or xpath) can be accomodated here.
The objects of page classes are created in Testclass where the methods of Page class is called.

The Test Class contains the method call to page class as well as the inputs that has to be passed alongwith it.
The Test class gets inputs from data provider and pass the same to appropriate methods.
Any changes in flow logics can be accomodated here. Additional methods created in page class can be called here.

** DataProvider **

Any framework requires mechanism to provide input to the target application. This can be acheived by dataprovider. 

The framework provides excel dataprovider where the data is inputted through excel file.
The excel has key value pairs where the key corresponds to the parameter name of the element and value contains the input value for the element.
When there is a change in input, we just need update the key value pairs for change in parameter name or value. 
*config.properties* file contains the environment variables and other variables that support automation

** TestNG Suites **

The execution of the test methods in test class is taken care by TestNG xml file.
One can design multiple xml file for different purposes like sanity, regression, sample cases etc.

** Maven **

Maven is a used in this framework as a dependency manager
The required dependency will be managed by maven.

** Report **

For reporting, Extent Reports are used.
This wil give a comprehensive report of the methods passed and failed along with percentage and screenshots.

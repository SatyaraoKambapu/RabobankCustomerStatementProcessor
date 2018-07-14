# RabobankCustomerStatementProcessor

Rabobank Customer Statement Processor





Rabobank receives monthly deliveries of customer statement records. This information is delivered in two formats, CSV and XML. These records need to be validated.



Output



There are two validations:


•all transaction references should be unique


•the end balance needs to be validated



At the end of the processing, a report needs to be created which will display both the transaction reference and description of each of the failed records.

Steps to Execute the Program:
* Input: You need to enter the proper file path (Either xml or csv file path)
* Output: You can see the report generation for failured records information.

#Used Design Patterns:
* Singleton design pattern - for report generation
* Factory design pattern - for file type reader class creation.

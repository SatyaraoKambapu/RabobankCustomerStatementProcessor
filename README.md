# RabobankCustomerStatementProcessor

Rabobank Customer Statement Processor





Rabobank receives monthly deliveries of customer statement records. This information is delivered in two formats, CSV and XML. These records need to be validated.


Input



The format of the file is a simplified format of the MT940 format. The format is as follows:

Table 1. Record description





Field

Description



Transaction reference
 
A numeric value
 

Account number
 
An IBAN
 

Start Balance
 
The starting balance in Euros
 

Mutation
 
Either and addition (+) or a deducation (-)
 

Description
 
Free text
 

End Balance
 
The end balance in Euros
 


Output



There are two validations:


•all transaction references should be unique


•the end balance needs to be validated



At the end of the processing, a report needs to be created which will display both the transaction reference and description of each of the failed records.

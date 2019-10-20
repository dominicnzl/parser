# Parser
Parser is a Java application that takes a .csv or .xml file and performs an import and validation on the data within. For this exercise, the imported files are simplified MT940 statements. The validation is performed to check for duplicate references, and to check if the start balance corrected by the mutation adds up to the end balance.

## Input
| Field | Description |
| :--- | :--- |
| Transaction reference | A numeric value |
| Account number | An IBAN |
| Start Balance | The starting balance in Euros |
| Mutation | Either an addition (+) or a deduction (-) |
| Description | Free text |
| End Balance | The end balance in Euros |

## Output
There are two validations:
* all transaction references should be unique
* the end balance needs to be validated

At the end of the processing, a report needs to be created which will display both the transaction reference and description of each of the failed records.

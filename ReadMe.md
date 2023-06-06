**Clustered Data Warehouse**

This is a guide to help you understand the implementation of the clustered data warehouse developed by our Scrum team
for Bloomberg to analyze FX deals. The purpose of this data warehouse is to accept deal details and persist them into a
database for further analysis.

**Introduction**

The clustered data warehouse is designed to handle and store FX deal details efficiently. It provides a reliable and
scalable solution for Bloomberg to analyze and gain insights from the deals data. The data warehouse accepts deal
details from external sources and persists them into a database, enabling easy retrieval and analysis of the data.

**System Requirements**

To run the clustered data warehouse, you need the following:

1. Java version 11
2. Maven
2. MySQL database
3. Docker

**Installation and running**

To install and set up the clustered data warehouse, all you need to follow steps over makefile.

**Usage**

Once the data warehouse is installed and run, you can start using it to accept deal details and persist them into
the database. Here's a CURL example of how to use the data warehouse:

`curl --location --request POST 'http://localhost:8080/deal' \
--header 'Content-Type: application/json' \
--data-raw '{
"fromCurrency":"USD",
"toCurrency":"JOD",
"timestamp":-1465188391000,
"amount": 0.1
}'`

**Database Schema**

The clustered data warehouse follows a specific database schema to organize the deal details. Here's an overview of the
key tables and their columns:

Table **deal** :

1. id
2. amount
3. from_currency
4. to_currency
5. timestamp

**Support**

If you encounter any issues or have questions related to the clustered data warehouse, please reach out to our support
team at nairoukhrahaf@gmail.com. We are here to assist you.
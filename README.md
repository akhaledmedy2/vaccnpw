# Run Command
# ------------ #
java -Xmx256m -jar vaccnpw-0.0.1.jar

# Change vaccination period default (15 minutes)
# ------------ #
add --schedule.vaccine.timeslot=900000 to the running command

Example:
--
java -Xmx256m -jar vaccnpw-0.0.1.jar --schedule.vaccine.timeslot=900000

# Change max number for vaccination per branch at time default (5 persons at a time)
# ------------ #
add schedule.vaccine.max=5 to the running command

Example:
--
java -Xmx256m -jar vaccnpw-0.0.1.jar --schedule.vaccine.max=5


# Change email username and password
# ------------ #
add spring.mail.username=?? and spring.mail.password=?? with email will send the confirmation to the running command

Example:
--
java -Xmx256m -jar vaccnpw-0.0.1.jar --spring.mail.username=?? , --spring.mail.password=??

# Payment Methods
# ------------ #
CASH -> 1 , CREDIT -> 2 , FAWRY -> 3

# Note :-
Attached with the project postman collection as JSON file
add the collection to postman to test the endpoints

Unit Testing  applied only on availability endpoints and services
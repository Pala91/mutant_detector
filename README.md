Mutant Detector
==============================

Api to find mutants from their DNA sequence

It exposes two different endpoints, one to analyze the DNA sequence and a second one to get the amount of mutants and the ratio over the total of sequences analyzed.

It is deployed in an AWS environment, in the base url: http://mutantdetector-env.eba-s9aczwxh.us-east-1.elasticbeanstalk.com/
 
The API implements an H2 database, but it could be modified to any relational database.

All the classes has over 80% coverage test, done with Jupiter.

###mutant endpoint
To consume the analyze endpoint, you should call the "/mutant" Post service, with the ValidationRq json object as parameter.
If the sequence corresponds to a Mutant, the response will be a Http status 200, otherwise it returns a Http status 403     
This is a request example:

{\
"dna": [\
"ATGCGA",\
"CTGTAC",\
"TTATCT",\
"AGACGG",\
"CTCCTA",\
"TCACTG"\
]\
}

Expose in: http://mutantdetector-env.eba-s9aczwxh.us-east-1.elasticbeanstalk.com/mutant
 

###/stats endpoint
The statistics service should call by the "/stats" Get endpoint without parameters and responses a statistical object and a Http status 200

This is a response example:\
{\
"count_mutant_dna": 1,\
"count_human_dna": 0,\
"ratio": 1\
}

Expose in: http://mutantdetector-env.eba-s9aczwxh.us-east-1.elasticbeanstalk.com/stats
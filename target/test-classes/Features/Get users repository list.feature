Feature: Get users repository list

@git_hub_api
Scenario: Get users repositories

Given The base uri is initialized
And Header "Accept" is "application/vnd.github.v3+json"
And User sends GET request to the endpoint for user "LiliiaKulish" 
And User saves response for Get request
And User gets the list of repositories for user "LiliiaKulish"
And User validates status code 200

And User sends GET request to the endpoint for user "LiliiaKulish99" 
And User saves response for Get request
And User validates status code 404 
And User validates message "Not Found"





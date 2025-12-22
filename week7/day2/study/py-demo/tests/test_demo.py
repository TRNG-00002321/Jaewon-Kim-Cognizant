import requests
import pytest

# Or import specific components
from requests import Session
from requests.auth import HTTPBasicAuth
from requests.exceptions import RequestException

baseUrl = "https://jsonplaceholder.typicode.com"

def test_get_all_users():
    response = requests.get(baseUrl+"/users")
    
    # print(response.json())   
    
    assert response.status_code == 200
    assert response.ok == True
    
def test_get_comments_by_params():
    params = {
        "postId": 1
    }
    response = requests.get(baseUrl+"/comments", params=params)
    # print(response.json()) 
    assert response.status_code == 200

def test_post_post():
    post_data = {
        "userId": 1,
        "title": "title",
        "body": "body"
    }
    response = requests.post(baseUrl+"/posts", json=post_data)
    # print(response.json()) 
    assert response.status_code == 201
    
def test_invalid_endpoint():
    response = requests.post(baseUrl+"/ERROR_ENDPOINT")
    # print(response.json()) 
    assert response.status_code == 404
    

    

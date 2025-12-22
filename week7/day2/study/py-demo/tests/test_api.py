import requests
import pytest

@pytest.fixture(scope="module")
def base_url():
    return "https://jsonplaceholder.typicode.com"


@pytest.fixture(scope="module")
def session():
    session = requests.Session()
    session.headers.update({
        "Accept":"application/json",
        "Content-Type": "application/json"
    })
    yield session
    session.close()

@pytest.fixture(scope="module")  
def sample_post():
    return {
        "userId": 1,
        "title": "Test Title",
        "body": "Test body"
    }
    
class TestBasicRequest():
    def test_get_single_post(self, base_url, session):
        response = session.get(f"{base_url}/posts/1")
        data = response.json()
        assert data["id"] == 1
        assert response.status_code == 200
        assert "title" in data
        
    
    @pytest.mark.parametrize("post_id", [
        (1),
        (2),
        (3),
        (11),
    ])
    def test_get_single_post_parameterized(self, base_url, session, post_id):
        response = session.get(f"{base_url}/posts/{post_id}")
        data = response.json()
        assert data["id"] == post_id
        assert response.status_code == 201
        assert "title" in data
        
    def test_post_new_post(self, base_url, session, sample_post):
        response = session.post(f"{base_url}/posts", json=sample_post)
        data = response.json()
        assert response.status_code == 201
        assert "title" in data
        
    @pytest.mark.parametrize("user_id,expected_name", [
        (1, "Leanne Graham"),
        (2, "Ervin Howell"),
        (3, "Clementine Bauch"),
        (4, "Patricia Lebsack"),
        (5, "Chelsey Dietrich")
    ])
    def test_user_names(self, base_url, session, user_id, expected_name):
        """Test user names match expected values"""
        response = session.get(f"{base_url}/users/{user_id}")

        assert response.status_code == 200
        assert response.json()["name"] == expected_name
        
    @pytest.mark.parametrize("endpoint,expected_count", [
        ("/posts", 100),
        ("/users", 10),
        ("/comments", 500),
        ("/albums", 100),
        ("/photos", 5000),
        ("/todos", 200)
    ])
    def test_resource_counts(self, base_url, session, endpoint, expected_count):
        """Test that each endpoint returns expected number of items"""
        response = session.get(f"{base_url}{endpoint}")

        assert response.status_code == 200
        assert len(response.json()) == expected_count
import pytest


@pytest.fixture(scope="module")
def sample_data():
    data={"name":"Alex","age":22}
    yield data
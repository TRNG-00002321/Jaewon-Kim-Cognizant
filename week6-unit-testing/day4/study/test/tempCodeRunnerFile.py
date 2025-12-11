def resource_fixture():
    print("\nSetting up resource...")
    resource = "my_shared_resource"
    yield resource  # The value 'resource' is yielded to the test function
    print("Tearing down resource...")
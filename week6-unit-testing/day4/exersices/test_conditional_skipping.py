import pytest
import sys
import platform

#Task1
@pytest.mark.skip(reason="Feature not implemented yet")
def test_future_feature():
    """This test is for a feature we haven't built yet."""
    from myapp import future_feature
    assert future_feature() == "working"


@pytest.mark.skip(reason="Broken after refactoring, fix in JIRA-123")
def test_broken_after_refactor():
    """This test broke and needs investigation."""
    assert False
    
#Task2
@pytest.mark.skipif(
    sys.platform != "win32",
    reason="Windows-specific functionality"
)
def test_windows_registry():
    """Test Windows registry operations."""
    import winreg
    # Windows-specific test code
    assert True


@pytest.mark.skipif(
    sys.platform != "linux",
    reason="Linux-specific functionality"
)
def test_linux_permissions():
    """Test Linux file permissions."""
    import pwd
    # Linux-specific test code
    assert True


@pytest.mark.skipif(
    sys.platform != "darwin",
    reason="macOS-specific functionality"
)
def test_macos_keychain():
    """Test macOS Keychain access."""
    # macOS-specific test code
    assert True


@pytest.mark.skipif(
    platform.machine() != "x86_64",
    reason="Requires x86_64 architecture"
)
def test_x86_specific():
    """Test that requires specific CPU architecture."""
    assert True

#Task3
@pytest.mark.skipif(
    sys.version_info < (3, 10),
    reason="Requires Python 3.10+ for match statement"
)
def test_match_statement():
    """Test using Python 3.10+ match statement."""
    value = "test"
    match value:
        case "test":
            result = True
        case _:
            result = False
    assert result


@pytest.mark.skipif(
    sys.version_info >= (3, 12),
    reason="Deprecated in Python 3.12"
)
def test_deprecated_feature():
    """Test for feature deprecated in newer Python."""
    assert True


# Reusable skip conditions
requires_python_310 = pytest.mark.skipif(
    sys.version_info < (3, 10),
    reason="Requires Python 3.10+"
)

@requires_python_310
def test_another_310_feature():
    assert True

#Task4
try:
    import pandas
    HAS_PANDAS = True
except ImportError:
    HAS_PANDAS = False

try:
    import numpy
    HAS_NUMPY = True
except ImportError:
    HAS_NUMPY = False


@pytest.mark.skipif(not HAS_PANDAS, reason="pandas not installed")
def test_dataframe_operations():
    """Test requiring pandas."""
    import pandas as pd
    df = pd.DataFrame({"a": [1, 2, 3]})
    assert len(df) == 3


@pytest.mark.skipif(not HAS_NUMPY, reason="numpy not installed")
def test_numpy_operations():
    """Test requiring numpy."""
    import numpy as np
    arr = np.array([1, 2, 3])
    assert arr.sum() == 6


# Using importorskip
def test_with_importorskip():
    """Dynamically skip if import fails."""
    requests = pytest.importorskip("requests")
    response = requests.get("https://httpbin.org/get")
    assert response.status_code == 200

#Task5
@pytest.mark.xfail(reason="Known bug, fix in progress")
def test_known_bug():
    """This test exposes a known bug."""
    # When this starts passing, the xfail will alert us
    assert 1 + 1 == 3  # Bug: should be 2


@pytest.mark.xfail(
    strict=True,
    reason="This MUST fail, if it passes something is wrong"
)
def test_strict_xfail():
    """A test that should always fail."""
    assert False


@pytest.mark.xfail(
    sys.platform == "win32",
    reason="Flaky on Windows"
)
def test_sometimes_flaky():
    """Test that's flaky on certain platforms."""
    import random
    # Simulate flaky behavior
    assert random.choice([True, True, True, False])


@pytest.mark.xfail(raises=ZeroDivisionError)
def test_specific_exception():
    """Expected to raise specific exception."""
    1 / 0
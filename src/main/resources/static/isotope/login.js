function checkInputValue() {
    const e = document.getElementById("email").value;
    const p = document.getElementById("password").value;

    if ('' == e) {
        alert("이메일을 입력해주세요.");
        return false;
    } else if ('' == p) {
        alert("비밀번호를 입력해주세요.");
        return false;
    }

    const d = new Object();
    d.id = e;
    d.password = p;

    return d;
}

function loginProcess() {
    let options = {
        method: 'POST',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(checkInputValue())
    }

    sendAjax("/login/check", options, "/", "이메일 또는 비밀번호가 틀립니다.", "");
}

function createUser() {
    let options = {
        method: 'POST',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(checkInputValue())
    }

    sendAjax("/login/create", options, "/login", "사용중인 아이디입니다.", "회원가입이 완료되었습니다.");
}

function sendAjax(urlAddress, options, returnAddress, errorMessage, successMessage) {
    let submit = async () => {
        let url = urlAddress;

        try {
            let response = await fetch(url, options);
            let json = await response;
            console.log(json);
            if (json.status == 200) {
                if ("" != successMessage) {
                    alert(successMessage);
                }
                location.href = returnAddress;
            } else {
                alert(errorMessage);
            }
        } catch (err) {
            alert("오류가 발생하였습니다.");
            console.log(err);
        }
    }

    submit();
}
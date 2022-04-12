function loginProcess() {
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

    let options = {
        method: 'POST',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(d)
    }

    sendAjax("/login/check", options, "/", "이메일 또는 비밀번호가 틀립니다.");
}

function sendAjax(urlAddress, options, returnAddress, errorMessage) {
    let submit = async () => {
        let url = urlAddress;

        try {
            let response = await fetch(url, options);
            let json = await response;
            console.log(json);
            if (json.status == 200) {
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
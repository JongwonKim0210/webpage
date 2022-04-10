function movePage(e) {
    locationPage(e.getAttribute("value"));
}

function movePrev() {
    let curPage = document.querySelector(".active").children[0].getAttribute("value");
    if (curPage > 10) {
        let q = document.querySelectorAll(".page-item");
        let el = q[q.length - 2].children[0].getAttribute("value");
        let t = (el * 1) - 10;
        locationPage(t);
    }
}

function moveNext() {
    let curPage = document.querySelector(".active").children[0].getAttribute("value");
    let q = document.querySelectorAll(".page-item");
    let el = q[1].children[0].getAttribute("value");
    let maxPage = q[q.length - 1].children[0].getAttribute("value");
    if (curPage < maxPage && (el + 10) < maxPage) {
        let t = (el * 1) + 10;
        locationPage(t);
    }
}

function locationPage(page) {
    location.href = window.location.pathname + "?page=" + page;
}

function moveTab(e) {
    const i = e.getAttribute("item1");
    const j = e.getAttribute("item2");
    const cmd = window.location.pathname.split("/")[1];
    location.href = "/" + cmd + "/" + i + "/" + j;
}

function showImage(e) {
    let a = e.getAttribute("src");
    let i = a.split("/")[a.split("/").length - 1];
    location.href = "/view/image/" + i;
}

function showDetail(e) {
    let i = e.getAttribute("item1");
    location.href = "/view/board/" + i;
}

function inputImageView() {
    const t = document.getElementById("imageViewer");
    const f = document.getElementById("fileExplode").files;
    for (let i = 0; i < f.length; i++) {
        let reader = new FileReader();
        let iT = document.createElement("img");
        iT.className = "image-view";
        reader.onload = function (e) {
            iT.setAttribute("src", e.target.result);
        }

        reader.readAsDataURL(f[i]);
        t.appendChild(iT);
    }

}

function imageUpload() {
    const f = document.getElementById("fileExplode").files;
    const s = document.getElementById("image-category");
    const mi = s.getAttribute("value");
    const ti = s.options[s.selectedIndex].value;
    if (f.length == 0) {
        alert("파일을 등록해주세요.");
        return false;
    }

    let fd = new FormData();
    fd.append("menuId", mi);
    fd.append("tabId" ,ti);
    for (let i = 0; i < f.length; i++) {
        fd.append("files", f[i]);
    }

    let options = {
        method: 'POST',
        body: fd
    }

    sendAjax('/api/put/image', options, "/details/" + mi + "/" + ti)
}

function usePasswd() {
    const c = document.getElementById("secret").checked;
    document.getElementById("passwd").disabled = !c;
}

function boardUpload() {
    let passwd = "";
    let content = editor.getHTML();
    let contentInfo = document.getElementById("contentArea");
    let returnAddress = "/details/" + contentInfo.getAttribute("item1") + "/" + contentInfo.getAttribute("item2");
    let title = document.getElementById("title").value;
    let usePasswd = document.getElementById("secret").checked;
    if (document.getElementById("secret").checked) {
        passwd = document.getElementById("passwd").value;
    }

    if (null == title || "" == title) {
        alert("제목을 입력해주세요");
        return false;
    }

    if (usePasswd && "" == passwd) {
        alert("비밀번호를 입력해주세요.");
        return false;
    }

    const data = new Object();
    data.title = title;
    data.content = content;
    data.menuId = contentInfo.getAttribute("item1");
    data.tabId = contentInfo.getAttribute("item2");
    if (usePasswd) {
        data.password = passwd;
    }

    let options = {
        method: 'POST',
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    }

    sendAjax("/api/put/board", options, returnAddress);
}

function sendAjax(urlAddress, options, returnAddress) {
    let submit = async () => {
        let url = urlAddress;

        try {
            let response = await fetch(url, options);
            let json = await response;
            console.log(json);
            if (json.status == 200) {
                alert("저장하였습니다.");
                location.href = returnAddress;
            } else {
                alert("저장 중 오류가 발생하였습니다.\t" + json.status);
            }
        } catch (err) {
            alert("저장 중 오류가 발생하였습니다.");
            console.log(err);
        }
    }

    submit();
}
function goAddSection() {
    alert("test");
    $.get("/section/goAdd.do");
    return false;
}

function goSelectSection() {
    $.post("/section/goSelect.do");
}

function goBack() {
    $.post("/welcome.do");
}
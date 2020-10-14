/*
$('#loginModal').modal({
    keyboard: false,
    backdrop: "static"
})*/


document.addEventListener('keydown', (event) => {
    if (event.keyCode === 116) event.preventDefault();
})
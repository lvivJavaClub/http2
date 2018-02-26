(function () {
    window.onload = function () {
        for (var i = 1; i < 350; i++) {
            var oImg = document.createElement("img");
            oImg.setAttribute('src', '/' + i + '.jpg');
            document.body.appendChild(oImg);
        }
    }
})();

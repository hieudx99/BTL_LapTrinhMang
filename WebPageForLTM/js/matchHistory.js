function showMatchHistory(id) {
  const xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function () {
    if ((xhttp.readyState = 4 && xhttp.status == 200)) {
      window.location.href += "match_history.php";
      //   document.getElementById("player__list").innerHTML = xhttp.responseText;
    }
  };
  xhttp.open("GET", "php/get_data.php?action=view_history&id=" + id, true);
  xhttp.send();
}

function backToTheMainPage() {
  window.location.href = "/LapTrinhMang";
}

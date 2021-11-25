window.onload = () => {
  getSortedStatistic("point");
};

function moveToMatchHistoryPage(id) {
  window.location.href += "match_history.php";
  window.localStorage.setItem("id", id);
}

function getSortedStatistic(option) {
  const xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function () {
    if ((xhttp.readyState = 4 && xhttp.status == 200))
      document.getElementById("player-list").innerHTML = xhttp.responseText;
  };
  xhttp.open("GET", "php/get_data.php?action=sort&option=" + option, true);
  xhttp.send();
}

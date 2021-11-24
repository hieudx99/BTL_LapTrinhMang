window.onload = () => {
  getSortedStatistic("score");
};

function getSortedStatistic(option) {
  const xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function () {
    if ((xhttp.readyState = 4 && xhttp.status == 200))
      document.getElementById("player__list").innerHTML = xhttp.responseText;
  };
  xhttp.open("GET", "php/get_data.php?action=sort&option=" + option, true);
  xhttp.send();
}

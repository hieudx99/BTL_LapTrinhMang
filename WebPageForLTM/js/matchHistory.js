window.onload = () => {
  showMatchHistory(window.localStorage.getItem("id"));
};

function showMatchHistory(id) {
  const xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function () {
    if ((xhttp.readyState = 4 && xhttp.status == 200)) {
      document.getElementById("match-history-list").innerHTML =
        xhttp.responseText;
    }
  };
  xhttp.open(
    "GET",
    "php/get_data.php?action=view_match_history&id=" + id,
    true
  );
  xhttp.send();
}

function backToTheMainPage() {
  window.location.href = "/LapTrinhMang/WebPageForLTM/";
}

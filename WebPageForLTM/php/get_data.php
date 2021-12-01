<?php
require "db_connection.php";

if ($con) {
    if (isset($_GET["action"]) && $_GET["action"] == "sort") {
        sortPlayer($_GET["option"]);
    }

    if (isset($_GET["action"]) && $_GET["action"] == "view_match_history") {
        showMatchHistory($_GET["id"]);
    }

}

function getInitialData()
{
    require "db_connection.php";
    if ($con) {
        $seq_no = 0;
        $query = "SELECT * FROM tblPlayer";
        $result = mysqli_query($con, $query);
        while ($row = mysqli_fetch_array($result)) {
            $seq_no++;
            showPLayerRow($seq_no, $row);
        }
    }
}

function showPLayerRow($seq_no, $row)
{
    ?>
<tr role="button" onClick="moveToMatchHistoryPage(<?php echo $row['id'] ?>)">
    <th scope="row"><?php echo $seq_no; ?></th>
    <td><?php echo $row['username'] ?></td>
    <td><?php echo $row['point']; ?></td>
    <td><?php echo $row['winTotal']; ?></td>
    <td><?php echo $row['loseTotal']; ?></td>
</tr>
<?php
}

function sortPlayer($option)
{
    require "db_connection.php";
    if ($con) {
        $seq_no = 0;
        $query = "SELECT * FROM tblPlayer ORDER BY $option DESC";
        $result = mysqli_query($con, $query);
        while ($row = mysqli_fetch_array($result)) {
            $seq_no++;
            showPLayerRow($seq_no, $row);
        }
    }

}

function displayMatchHistory($row)
{
    ?>

<tr>
    <td><?php echo $row[0]; ?></td>
    <td><?php echo $row[1]; ?></td>
    <td><?php echo $row[2]; ?></td>
    <td><?php echo $row[3]; ?></td>
    <td><?php echo $row[4]; ?></td>
    <td><?php echo $row[5]; ?></td>
</tr>
<?php

}

function showMatchHistory($id)
{
    require "db_connection.php";
    if ($con) {
        $query1 = "SELECT * FROM tbldealtcard WHERE tblPlayerid = $id ORDER BY id DESC";
        $result1 = mysqli_query($con, $query1);
        while ($rowQuery1 = mysqli_fetch_array($result1)) {
            $data = array();
            $matchId = $rowQuery1['tblMatchid'];
            $selectedUserId = $rowQuery1['tblPlayerid'];
            $totalValueSelectedUser = $rowQuery1['totalValue'];

            // get match,date
            $data[] = $matchId;
            $query2 = "SELECT `date` FROM tblMatch WHERE id = $matchId";
            $result = mysqli_query($con, $query2);
            if (($row = mysqli_fetch_array($result))) {
                $data[] = $row['date'];
            }
            // get status selected user
            $winLoseStatus = $rowQuery1['position'];
            if ($winLoseStatus == 0) {
                $data[] = "Win";
            } else if ($winLoseStatus == 2) {
                $data[] = "Draw";
            } else {
                $data[] = "Lose";
            }

            // get opponent id, totalValue
            $matchId = $rowQuery1['tblMatchid'];
            $query3 = "SELECT * FROM tbldealtcard WHERE tblMatchid = $matchId AND tblPlayerid <> $selectedUserId";
            $result = mysqli_query($con, $query3);
            if (($row = mysqli_fetch_array($result))) {
                $totalValueOpponent = $row['totalValue'];
                $opponentId = $row['tblPlayerid'];
            }

            // get opponent name
            $queryUsernameOpponent = "SELECT username FROM tblplayer WHERE id = $opponentId";
            $result = mysqli_query($con, $queryUsernameOpponent);
            if (($row = mysqli_fetch_array($result))) {
                $data[] = $row['username'];
            }

            $data[] = $totalValueSelectedUser;
            $data[] = $totalValueOpponent;

            displayMatchHistory($data);
        }
    }
}

?>
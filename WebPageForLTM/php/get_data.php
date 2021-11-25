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
    <td><?php echo $row['firstRankTime']; ?></td>
    <td><?php echo $row['secondRankTime']; ?></td>
    <td><?php echo $row['thirdRankTime']; ?></td>
    <td><?php echo $row['lastRankTime']; ?></td>
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
</tr>
<?php

}

function showMatchHistory($id)
{
    require "db_connection.php";
    if ($con) {
        $queryMatch = "SELECT id, firstRank, secondRank, thirdRank, lastRank FROM tblMatch WHERE firstRank = $id OR secondRank =
$id OR thirdRank = $id OR lastRank = $id ORDER BY id DESC";
        $resultAllMatch = mysqli_query($con, $queryMatch);
        while ($rowQueryMatch = mysqli_fetch_array($resultAllMatch)) {
            $data = array();
            $data[] = $rowQueryMatch['id'];
            $firstRank = $rowQueryMatch['firstRank'];
            $secondRank = $rowQueryMatch['secondRank'];
            $thirdRank = $rowQueryMatch['thirdRank'];
            $lastRank = $rowQueryMatch['lastRank'];
            if ($firstRank != null) {
                $queryFirstRankUsername = "SELECT username FROM tblPlayer WHERE id = $firstRank";
                $result = mysqli_query($con, $queryFirstRankUsername);
                if (($row = mysqli_fetch_array($result))) {
                    $data[] = $row['username'];
                }
            } else {
                $data[] = "";
            }
            if ($secondRank != null) {
                $querySecondRankUsername = "SELECT username FROM tblPlayer WHERE id = $secondRank";
                $result = mysqli_query($con, $querySecondRankUsername);
                if (($row = mysqli_fetch_array($result))) {
                    $data[] = $row['username'];
                }
            } else {
                $data[] = "";
            }
            if ($thirdRank != null) {
                $queryThirdRankUsername = "SELECT username FROM tblPlayer WHERE id = $thirdRank";
                $result = mysqli_query($con, $queryThirdRankUsername);
                if (($row = mysqli_fetch_array($result))) {
                    $data[] = $row['username'];
                }
            } else {
                $data[] = "";
            }
            if ($lastRank != null) {
                $queryLastRankUsername = "SELECT username FROM tblPlayer WHERE id = $lastRank";
                $result = mysqli_query($con, $queryLastRankUsername);
                if (($row = mysqli_fetch_array($result))) {
                    $data[] = $row['username'];
                }
            } else {
                $data[] = "";
            }
            displayMatchHistory($data);
        }
    }
}

?>
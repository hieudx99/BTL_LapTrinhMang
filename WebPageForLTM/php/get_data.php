<?php
require "db_connection.php";

if ($con) {
    if (isset($_GET["action"]) && $_GET["action"] == "sort") {
        sortPlayer($_GET["option"]);
    }

    if (isset($_GET["action"]) && $_GET["action"] == "view_history") {
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
<tr onClick="showMatchHistory(<?php echo $row['id'] ?>)">
    <th scope="row"><?php echo $seq_no; ?></th>
    <td><?php echo $row['username'] ?></td>
    <td><?php echo $row['score']; ?></td>
    <td><?php echo $row['1stInGameCount']; ?></td>
    <td><?php echo $row['2ndInGameCount']; ?></td>
    <td><?php echo $row['3rdInGameCount']; ?></td>
    <td><?php echo $row['4thInGameCount']; ?></td>
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

function showMatchHistory($id)
{
    echo "haha";
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
?>
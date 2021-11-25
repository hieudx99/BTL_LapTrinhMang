<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous" />
    <title>Match History</title>
</head>

<body>
    <div class="container">
        <h3 class="text-center mb-3">Match History</h3>
        <div class="d-flex justify-content-center mb-4">
            <button type="button" class="btn btn-info" onClick="backToTheMainPage()">Back to the main page</button>
        </div>
        <table class="table table-striped border">
            <thead>
                <tr>
                    <th scope="col">Match ID</th>
                    <th scope="col">First Rank</th>
                    <th scope="col">Second Rank</th>
                    <th scope="col">Third Rank</th>
                    <th scope="col">Last Rank</th>
                </tr>
            </thead>
            <tbody id="match-history-list">
            </tbody>
        </table>
    </div>
    <script src=" js/matchHistory.js"></script>
</body>

</html>
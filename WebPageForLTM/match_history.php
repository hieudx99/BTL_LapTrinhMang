<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous" />
    <title>Match History</title>
    <link rel="stylesheet" href="style.css">

</head>

<body>
    <div class="container">
        <h3 class="text-center my-5">Match History</h3>
        <div class="d-flex justify-content-center mb-4">
            <button type="button" class="btn btn-info" id="back__button" onClick="backToTheMainPage()">Back to the main
                page</button>
        </div>
        <div class="wrapper__table">
            <table class="table table-striped border mb-0">
                <thead>
                    <tr>
                        <th scope="col">Match ID</th>
                        <th scope="col">Date</th>
                        <th scope="col">Status(Win, Lose, Draw)</th>
                        <th scope="col">Opponent name</th>
                        <th scope="col">Your point</th>
                        <th scope="col">Opponent point</th>
                    </tr>
                </thead>
                <tbody id="match-history-list">
                </tbody>
            </table>
        </div>
    </div>
    <script src=" js/matchHistory.js"></script>
</body>

</html>
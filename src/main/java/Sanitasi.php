#Filter Validation

<?php
//checks for posted data
/*
    if(filter_has_var(INPUT_POST,'data')){
        echo 'Data Found';
    }else{
        echo 'No Data';
    }
    */
if (filter_has_var(INPUT_POST, 'data')) {
    //remove illegal chars
    $email = $_POST['data'];
    $email = filter_var($email, FILTER_SANITIZE_EMAIL);
    echo $email . '<br>';

    //validate as an email address
    if (filter_input(INPUT_POST, 'data', FILTER_VALIDATE_EMAIL)) {
        echo 'Email is Valid';
    } else {
        echo 'Email is not Valid';
    }

    //we can use the below if we use the sanatizer above
    if (filter_var($email, FILTER_VALIDATE_EMAIL)) {
        echo 'Email is Valid';
    } else {
        echo 'Email is not Valid';
    }
}
#Other Validations
#FILTER_VALIDATE_BOOLEAN
#FILTER_VALIDATE_EMAIL
#FILTER_VALIDATE_FLOAT
#FILTER_VALIDATE_INT
#FILTER_VALIDATE_IP
#FILTER_VALIDATE_REGEXP  'REGULAR EXPRESSION'
#FILTER_VALIDATE_URL

#Other sanatizes
#FILTER_SANATIZE_EMAIL
#FILTER_SANATIZE_ENCODED
#FILTER_SANATIZE_NUMBER_FLOAT
#FILTER_SANATIZE_NUMBER_INT
#FILTER_SANATIZE_SPECIAL_CHARS
#FILTER_SANATIZE_STRING
#FILTER_SANATIZE_URL

//EXAMPLE int VALIDATION
$var = $_POST['data2'];
if (filter_var($var, FILTER_VALIDATE_INT)) {
    echo '<br>' . $var . ' is a number<br>';
} else {
    echo '<br>' . $var . ' is not a number' . '<br>';
}

//EXAMPLE int Sanitazion
$var2 = $_POST['data2'];
echo '<br>' . 'Hasil Sanitasi Number INT Dari Field Kedua : ' . filter_var($var2, FILTER_SANITIZE_NUMBER_INT) . '<br>';

//ARRAY USAGE
$filters = array(
    "data" => FILTER_VALIDATE_EMAIL,
    "data2" => array(
        "filter" => FILTER_VALIDATE_INT,
        "options" => array(
            "min_range" => 1,
            "max_range" => 100
        )
    )
);

?>

<form method="post" action="<?php echo $_SERVER['PHP_SELF']; ?>">
    <input type="text" name="data" placeholder="email@email.com">
    <input type="text" name="data2" placeholder="123123">
    <button type="submit">Submit</button>
</form>
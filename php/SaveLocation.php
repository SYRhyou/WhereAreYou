<?php
	$con = mysqli_connect("localhost", "fbtpduf", "fbtpduf94", "fbtpduf");
	
	$LATITUDE = $_POST["pointY"];
	$LONGITUDE = $_POST["pointX"];
	
	$statement = mysqli_prepare($con, "INSERT INTO SAVEDLOCATION VALUES (?,?)");
	mysqli_stmt_bind_param($statement, "ss", $LATITUDE, $LONGITUDE);
	mysqli_stmt_execute($statement);
	
	$response = array();
	$response["success"] = true;
	
	echo json_encode($response);
?>
	
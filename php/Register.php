<?php
	$con = mysqli_connect("localhost", "fbtpduf", "fbtpduf94", "fbtpduf");
	
	$userID = $_POST["userID"];
	$userPassword = $_POST["userPassword"];
	$userNickname = $_POST["userNickname"];
	
	$statement = mysqli_prepare($con, "INSERT INTO USERES VALUES (?,?,?)");
	mysqli_stmt_bind_param($statement, "sss", $userID, $userPassword, $userNickname);
	mysqli_stmt_execute($statement);
	
	$response = array();
	$response["success"] = true;
	
	echo json_encode($response);
?>
	
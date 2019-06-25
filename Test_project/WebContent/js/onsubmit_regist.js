/**
 * 
 */

// Below Function Executes On Form Submit
function ValidationEvent() {
	// Storing Field Values In Variables
	var username = document.forms["formreg"]["username"].value;
	var password = document.getElementById("password").value;
	var repassword = document.getElementById("repassword").value;
	var email = document.getElementById("email").value;
	var name = document.getElementById("name").value;
	var lastname = document.getElementById("last").value;
	// Regular Expression For Email
	var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
	// Conditions
	if (username != '' && email != '' && name != '' && lastname != ''
			&& password != '' && repassword != '') {
		if (email.match(emailReg)) {
			if (document.getElementById("optionsRadiosMale").checked
					|| document.getElementById("optionsRadiosFemale").checked) {
				if ((password).match(repassword)) {

					if (password.length >= 6) {
						alert("All type of validation has done on OnSubmit event.");
						return true;
					} else {
						alert("The Contact No. must be at least 10 digit long!");
						return false;
					}
				}
			} else {
				alert("You must select gender.....!");
				return false;
			}
		} else {
			alert("Invalid Email Address...!!!");
			return false;
		}
	} else {
		alert("All fields are required.....!");
		return false;
	}
}
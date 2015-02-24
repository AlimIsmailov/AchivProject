$('document').ready(function() {
    toggleRadioButton();
    toggleButoonSearchOptions();
});

function toggleButoonSearchOptions() {
	var searchingOptionsContainer = document.getElementById("SearchingOptionsContainer");
	var advancedOptionsBtn = document.getElementById("advancedOptionsBtn");
	
if (advancedOptionsBtn.checked)
	searchingOptionsContainer.className = "row top-buffer";
else
	searchingOptionsContainer.className = "row top-buffer hide";
}

	function toggleRadioButton() {
		var byNameRadioBtn = document.getElementById("byNameRadioBtn");
		var byEmailBtn = document.getElementById("byEmailBtn");
		var surNameField = document.getElementById("SurNameField");
		var nameField = document.getElementById("NameField");
		var byLoginBtn = document.getElementById("byLoginBtn");
		var nameFieldContainer = document.getElementById("NameFieldContainer");
		var surNameFieldContainer = document.getElementById("SurNameFieldContainer");

		if (byNameRadioBtn.checked) {
			nameFieldContainer.className = "col-md-5";
			surNameFieldContainer.className = "col-md-5";
			nameField.placeholder = "Name";
			surNameField.setAttribute("type", "text");
			surNameField.placeholder = "Surname";
		}

		if (byLoginBtn.checked) {
			nameFieldContainer.className = "col-md-10";
			surNameFieldContainer.className = "";
			surNameField.setAttribute("type", "hidden");
			nameField.placeholder = "Login";
		}

		if (byEmailBtn.checked) {
			nameFieldContainer.className = "col-md-10";
			surNameFieldContainer.className = "";
			surNameField.setAttribute("type", "hidden");
			nameField.placeholder = "Email";
		}
	}
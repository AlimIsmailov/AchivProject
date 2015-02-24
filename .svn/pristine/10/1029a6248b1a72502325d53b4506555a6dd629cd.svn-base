$(document).ready(function() {
	$('.form-validate').validate({
		rules : {
			name : {
				required : true
			},
			start: {
				required: true,
				myDate: true
			},
			end: {
				required: true,
				myDate: true
			}
		},
		messages : {
			name : {
				required : "Please enter name"
			}
		},
		submitHandler : function(form) {
			form.submit();
		}
	})
});

$.validator.addMethod("myDate", function(value, element) {
	return value.match(/^\d{4}[\/\-](0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])$/);
}, "Please enter a valid date in the format YYYY-MM-DD.");
function getStats() {
	$.ajax({
		type : 'GET',
		contentType : 'application/json',
		url : "/v1/stats",
		dataType : "json",
		success : function(data, textStatus, jqXHR) {
			afficheUser(data);
			afficheEvents(data);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log('getStat error: ' + textStatus);
		}
	});
}


function afficheUser(data) {
	console.log(data);
	$("#odoUsers").html(data.nmbUsers);
}

function afficheEvents(data) {
	console.log(data);
	//$("#odoEvent").html(Math.floor(Math.random()*2));
	$("#odoEvent").html(data.nmbEvents);
}

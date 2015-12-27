/**
 * 
 */

function uploadImage() {
	var file = document.querySelector('input[type=file]').files[0];
	
	if (file) {
      var reader = new FileReader();
      var encoded;
      reader.readAsDataURL(file);
      reader.onload = function(e) { 
    	  encoded = e.target.result;
    	  var imgEncode;
    	  if (encoded.indexOf("data:image/jpeg;base64,") != -1) {
    		  var imgEncoded = encoded.replace("data:image/jpeg;base64,", "");
    	  }
    	  else if (encoded.indexOf("data:image/jpg;base64,") != -1) {
    		  var imgEncoded = encoded.replace("data:image/jpg;base64,", "");
    	  }
    	  else if (encoded.indexOf("data:image/png;base64,") != -1) {
    		  var imgEncoded = encoded.replace("data:image/png;base64,", "");
    	  }
    	  $.ajax({ 
    		    url: 'https://api.imgur.com/3/image',
    		    headers: {
    		        'Authorization': 'Client-ID 9a8594613d16dd6'
    		    },
    		    type: 'POST',
    		    data: {
    		        'image': imgEncoded
    		    },
    		    success: function(data) { 
    		    	console.log(data.data.link); 
    		    }
    		});
	  }
	} 
}

function doUploadAd() {
	
}
/**
 * 
 */
 function addAnimal()
 {
	
	 fetch("/animal/showform")
        .then(response => {
          loadCaptcha();  
        })
        .catch(error => {
            // Handle errors that occur during fetch or JSON parsing
            console.error('Error:', error);
        });
}

 
 
 function loadCaptcha() {
            fetch('/captcha/generateCaptcha')
            .then(response => response.text())
            .then(data => {
                document.getElementById('"captchaDiv"').textContent = data;
            })
            .catch(error => console.error('Error fetching math CAPTCHA:', error));
        }
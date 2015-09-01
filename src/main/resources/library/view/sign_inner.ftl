<html lang="en">
<head>
    <meta name="google-signin-scope" content="profile email">
    <meta name="google-signin-client_id" content="YOUR_CLIENT_ID.apps.googleusercontent.com">
    <script src="https://apis.google.com/js/platform.js" async defer></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
</head>
<body>
<div id="user_details"></div>
<div class="g-signin2" data-onsuccess="onSignIn"></div>
<meta name="google-signin-client_id" content="863691724658-7g666g617bn75q24vov9ssuece7m80i1.apps.googleusercontent.com">

<div><a href="https://accounts.google.com/logout" onclick="Outter()">Sign out</a></div>
<div id = "stuff"></div>
<script>
    function Outter() {
        var auth2 = gapi.auth2.getAuthInstance();
        auth2.signOut().then(function () {
            console.log('User signed out.');
        });
        //GoogleAuth.signOut();
        //GoogleAuth.disconnect();
    }

    function onSignIn(googleUser) {
        // Useful data for your client-side scripts:
        //var profile = googleUser.getBasicProfile();
        //console.log("ID: " + profile.getId()); // Don't send this directly to your server!
        //console.log("Name: " + profile.getName());
        //console.log("Image URL: " + profile.getImageUrl());
        //console.log("Email: " + profile.getEmail());

        // The ID token you need to pass to your backend:
        var id_token = googleUser.getAuthResponse().id_token;
        window.location.assign("http://localhost:8080/modify/books/" + id_token);
    }
</script>
</body>
</html>
function focusMessage() {
  if(!document.getElementById("messageBox").hasAttribute("hidden")) {
    window.location.hash = '#messageBox';
  }
}

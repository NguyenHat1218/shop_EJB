function collapseSidebar(){
    document.body.classList.toggle('sidebar-expand');
    subMenu.forEach(item => {
            item.children[1].classList.remove('active');
    });
}

window.onclick = function(event){
    openCloseDropdown(event);
}

// close all dropdown
function closeAllDropdown(){
    var dropdowns = document.querySelectorAll('.dropdown-expand');
    dropdowns.forEach(drops => {
        drops.classList.remove('dropdown-expand');
    })
}

function openCloseDropdown(event){
    if(!event.target.matches('.dropdown-toggle')){
        // close dropdown when click outside menu
        closeAllDropdown();
    }
    else{
        var toggle = event.target.dataset.toggle;
        //console.log(toggle);
        var content = document.querySelector(`#${toggle}`);
        //console.log(content);
        if(content.classList.contains('dropdown-expand')){
            closeAllDropdown();
        }
        else{
            closeAllDropdown();
            content.classList.add('dropdown-expand');
        }
    }
}
// body
document.body.classList.add("overlay-scrollbar");
document.body.classList.add("sidebar-expand");
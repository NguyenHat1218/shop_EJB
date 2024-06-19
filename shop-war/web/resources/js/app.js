
var button_menu = document.querySelector('.button-menu');

// nhấn mở menu
button_menu.addEventListener('click',function(){
    document.body.classList.toggle('menu-open');
});
// nhấn đóng menu
document.querySelector('#close-sidebar-nav').addEventListener('click',function(){
    document.body.classList.toggle('menu-open');
});
//
const has_child = document.querySelectorAll('.has-child');
has_child.forEach(child => {
    child.addEventListener('click',function(e){
        e.preventDefault();
        // has_child.forEach(item => item.children[1].classList.remove("show")); // đóng cái vừa mở
        //console.log(this);
        var sub = child.children[1];
        sub.classList.toggle('show');
    });
});
// window onclick
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
// tabcontent
function closeTabContent(bool){
    var tabContent = document.querySelectorAll('.tabcontent');
    var tabLinks = document.querySelectorAll('.tablinks');
    var i= 0;
    var j = 0;
    if(bool == false){
        tabContent.forEach(item => item.style.display = "none");
        tabLinks.forEach(item => item.classList.remove("active"));
    }
    else{
        tabContent.forEach(item => {
            if(i != 0){
                item.style.display = "none";
            }
            i++;
        });
        tabLinks.forEach(item => {
            if(j != 0){
                item.classList.remove("active");
            }
            j++;
        });
    }
}
var tabLinks = document.querySelectorAll(".tablinks");
tabLinks.forEach(item => {
    item.addEventListener('click',(e) => {
        closeTabContent(false);
        let tabContent = item.getAttribute("data-id");
        //alert(tabContent);
        item.classList.add('active');
        document.querySelector("#"+tabContent).style.display = "block";

    });
});
closeTabContent(true);
// review product
var productSmall = document.querySelectorAll(".product-review-small");
var productMain = document.querySelector(".product-highlight");
productSmall.forEach(item => {
    item.addEventListener('click',(e) => {
        productMain.src = item.src;
        productSmall.forEach(small => small.classList.remove("active"));
        item.classList.add("active");
    });
});
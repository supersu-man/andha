const downloadButton1 = document.getElementById('downloadButton1')
const downloadButton2 = document.getElementById('downloadButton2')

const toggle = document.getElementById('themeSwitch')
const body = document.body
const slider = document.getElementById('slider');
const textContainer = document.getElementById('text-container')

textContainer.style.fontSize = '100px'
slider.oninput = () => {
    textContainer.style.fontSize = slider.value * 2 + 'px'
}

if (localStorage.getItem('dark-theme')) {
    body.classList.remove('light-theme')
    body.classList.add('dark-theme')
    toggle.setAttribute('checked', 'true')
}

toggle.onchange = () => {
    if (toggle.checked) {
        body.classList.remove('light-theme')
        body.classList.add('dark-theme')
        localStorage.setItem('dark-theme', true)
    } else {
        body.classList.add('light-theme')
        body.classList.remove('dark-theme')
        localStorage.removeItem('dark-theme')
    }
}

downloadButton1.addEventListener('click', () => {
    window.open('https://github.com/supersu-man/andha/releases', '_self')
})

downloadButton2.addEventListener('click', () => {
    window.open('https://play.google.com/store/apps/details?id=com.supersuman.andha', '_self')
})
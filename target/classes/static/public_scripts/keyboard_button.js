let activated = false
let visible = false


$('#show_keyboard').click(() => {
  if (!activated) {
    $('input').mlKeyboard({
      layout: 'es_ES',
      active_shift: false,
      open_speed: 300,
      close_speed: 100,
      is_hidden: false
      //trigger: '#test3'
    });
    activated = true
    visible = true
  } else {
    if (visible) {
      $('#mlkeyboard').attr('hidden', true)
      visible = false
    } else {
      $('#mlkeyboard').attr('hidden', false)
      visible = true


    }
  }
});
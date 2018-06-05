
      var baseImg = document.getElementById('baseImg');
      var canvas = document.getElementById('viewport'),
      context = canvas.getContext('2d'),
      skirtCanvas = document.getElementById('skirt'),
      skirt = skirtCanvas.getContext('2d'),
      handCanvas = document.getElementById('hands'),
      hand = handCanvas.getContext('2d'),
      skirtfabricCanvas = document.getElementById('skirtfabric'),
      skirtfabric = skirtfabricCanvas.getContext('2d'),
      topCanvas = document.getElementById('top'),
      tops = topCanvas.getContext('2d');
      var fabricPath = [
          {x:138,y:217},
          {x:136,y:242},
          {x:136,y:260},
          {x:142,y:287},
          {x:147,y:309},
          {x:152,y:328},
          {x:161,y:347},
          {x:156,y:359},
          {x:151,y:375},
          {x:163,y:381},
          {x:171,y:382},
          {x:189,y:383},
          {x:203,y:381},
          {x:210,y:379},
          {x:218,y:374},
          {x:216,y:360},
          {x:214,y:332},
          {x:220,y:309},
          {x:224,y:287},
          {x:231,y:258},
          {x:234,y:234},
          {x:234,y:219},
          {x:230,y:208},
          {x:223,y:197},
          {x:211,y:186},
          {x:196,y:179},
          {x:185,y:183},
          {x:176,y:185},
          {x:163,y:186},
          {x:150,y:182},
          {x:143,y:196}
      ]

      make_base();

      function make_base()
      {
        base_image = new Image();
        base_image.src = 'images/base.png';
        hand_image = new Image();
        hand_image.src = 'images/base_hand.png';
        skirt_image = new Image();
        skirt_image.src = 'images/trans.png';
        base_image.onload = function(){
          canvas.height = baseImg.height ;
          canvas.width = baseImg.width ;
          skirtCanvas.height = baseImg.height ;
          skirtCanvas.width = baseImg.width ;
          handCanvas.height = baseImg.height;
          handCanvas.width = baseImg.width;
          skirtfabricCanvas.height = baseImg.height;
          skirtfabricCanvas.width = baseImg.width;
          topCanvas.height = baseImg.height;
          topCanvas.width = baseImg.width;
          hand.drawImage(hand_image, 0, 0,baseImg.width,baseImg.height);
          context.drawImage(base_image, 0, 0,baseImg.width,baseImg.height);
          skirt.drawImage(skirt_image,0,0,baseImg.width,baseImg.height);
          skirtfabric.beginPath();
          fabricPath.forEach(function(item){
            skirtfabric.lineTo(item.x,item.y);
          })
          skirtfabric.closePath();
        }
      }
      $(document).ready(function(){
        $('.get_skirts').click();
        $('.get_top').click();
        $('#hands').click();
      })
      $('.get_skirts').click(function(){
        var Thissrc = $(this).data('src');
        skirtImage = new Image();
        skirtImage.src = Thissrc;
        skirt.drawImage(skirtImage, 0, 0,baseImg.width,baseImg.height);
      })
      $('.get_top').click(function(){
        var Thissrc = $(this).data('src');
        topImage = new Image();
        topImage.src = Thissrc;
        tops.drawImage(topImage, 0, 0,baseImg.width,baseImg.height);
      })
      var count = 0 ;
      $('#hands').click(function(){
        if(count == 0){
          hand_image = new Image();
          hand_image.src = 'images/base_hand.png';
          hand.drawImage(hand_image, 0, 0,baseImg.width,baseImg.height);
          count++;
        }
      })
      $('.get_fabric').click(function(){
          
          var fabric=  new Image();
          fabric.src = $(this).attr('src');
          var pat=skirtfabric.createPattern(fabric,"repeat");
          skirtfabric.fillStyle=pat;
          skirtfabric.fill();
      })
      
    

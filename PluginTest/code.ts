"use strict";


if (figma.editorType === 'figma') {
    // This plugin will open a window to prompt the user to enter a number, and
    // it will then create that many rectangles on the screen.
     // This shows the HTML page in "ui.html".
    figma.showUI(__html__);

    let allLayer = figma.currentPage;
    console.log("plugin testing....");
    var childrens:any = {};
    childrens = allLayer.children;
    console.log(childrens[0].children);
    let layers = childrens[0].children;
    var designElements = [];
    for (let index = 0; index < layers.length; index++) {
        var designElement:any = {};
        const element = layers[index];
        designElement.type = element.type;
        var cssMapperObj = processLayerCSSMapper(element);
        designElement.cssProperties = cssMapperObj;
        designElements.push(designElement);
    }
    console.log("before async");
    // async () => {
    //   console.log("async");
    //   const response = await fetch('http://localhost:8080/process', {'method' : 'post'});
    //   const json = await response.json()
    
    //   console.log(JSON.stringify(json.args, null, 2))
    
    //   // figma.closePlugin()
    // }
    callAPI(designElements);
    // var response = {};
    // response = callAPI();
    // console.log(response);
    // Make sure to close the plugin when you're done. Otherwise the plugin will
    // keep running, which shows the cancel button at the bottom of the screen.
    // figma.closePlugin();
}
;
// Runs this code if the plugin is run in FigJam
if (figma.editorType === 'figjam') {
    // This plugin will open a window to prompt the user to enter a number, and
    // it will then create that many shapes and connectors on the screen.
    // This shows the HTML page in "ui.html".
    figma.showUI(__html__);
    // Calls to "parent.postMessage" from within the HTML page will trigger this
    // callback. The callback will be passed the "pluginMessage" property of the
    // posted message.
    figma.ui.onmessage = msg => {
        // One way of distinguishing between different types of messages sent from
        // your HTML page is to use an object with a "type" property like this.
        if (msg.type === 'create-shapes') {
            const numberOfShapes = msg.count;
            const nodes = [];
            for (let i = 0; i < numberOfShapes; i++) {
                const shape = figma.createShapeWithText();
                // You can set shapeType to one of: 'SQUARE' | 'ELLIPSE' | 'ROUNDED_RECTANGLE' | 'DIAMOND' | 'TRIANGLE_UP' | 'TRIANGLE_DOWN' | 'PARALLELOGRAM_RIGHT' | 'PARALLELOGRAM_LEFT'
                shape.shapeType = 'ROUNDED_RECTANGLE';
                shape.x = i * (shape.width + 200);
                shape.fills = [{ type: 'SOLID', color: { r: 1, g: 0.5, b: 0 } }];
                figma.currentPage.appendChild(shape);
                nodes.push(shape);
            }
            ;
            for (let i = 0; i < (numberOfShapes - 1); i++) {
                const connector = figma.createConnector();
                connector.strokeWeight = 8;
                connector.connectorStart = {
                    endpointNodeId: nodes[i].id,
                    magnet: 'AUTO',
                };
                connector.connectorEnd = {
                    endpointNodeId: nodes[i + 1].id,
                    magnet: 'AUTO',
                };
            }
            ;
            figma.currentPage.selection = nodes;
            figma.viewport.scrollAndZoomIntoView(nodes);
        }
        // Make sure to close the plugin when you're done. Otherwise the plugin will
        // keep running, which shows the cancel button at the bottom of the screen.
        figma.closePlugin();
    };
}
;
function processLayerCSSMapper(childLayer:any) {
    var cssAttributes:any = {};
    cssAttributes.width = childLayer.width;
    cssAttributes.heigth = childLayer.height;
    cssAttributes.xaxis = childLayer.x;
    cssAttributes.yaxis = childLayer.y;
    var colorObj:any = {};
    colorObj = processRBG(childLayer.fills);
    cssAttributes.r = colorObj.r;
    cssAttributes.b = colorObj.b;
    cssAttributes.g = colorObj.g;
    return cssAttributes;
}
function processRBG(fills:any) {
    var color:any = {};
    var r = 0;
    var b = 0;
    var g = 0;
    var len = fills.length;
    for (let a = 0; a < len; a++) {
        r += fills[a].color.r;
        b += fills[a].color.b;
        g += fills[a].color.g;
        console.log("type : " + fills[a].type + " color : R :" + fills[a].color.r + " G : " + fills[a].color.g + " B : " + fills[a].color.b);
    }
    if (len > 0) {
        color.r = r / len;
        color.b = b / len;
        color.g = g / len;
    }
    else {
        color.r = null;
        color.b = null;
        color.g = null;
    }
    return color;
}

async function callAPI(allLayer1:any){
  console.log("inside callapi");
  let toStrin = JSON.stringify(allLayer1);
  console.log(allLayer1);
  console.log(toStrin);
  const url = 'http://localhost:8080/process';
  const response = await fetch(url,{
    "method" : "post",
    "body" : toStrin,
    "headers": {
        "Content-Type": "application/json",
      },
  })
  const responsefromapi = await response.arrayBuffer();
  figma.ui.postMessage(responsefromapi)
//   console.log(responsefromapi.test);
//   console.log(response.ok);
//   console.log(response.status);
//   console.log(response.text);
}
